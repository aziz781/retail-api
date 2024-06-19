# # FROM java:8
# FROM arm64v8/openjdk:17-ea-16-jdk@sha256:149f7dbd5287cb06efc8c5d0dfffeffcc36e8a9872dca7736ef8c333a3eca6a2
# VOLUME /tmp
# ADD retail-api.jar app.jar
# RUN bash -c 'touch app.jar'
# ENTRYPOINT ["java","-jar","app.jar"]

FROM --platform="linux/arm64"  arm64v8/node@sha256:b16c4e21f9e9e4d02c226d7b2dde3283fc9315104b66009af546b50f5c7acad4 AS builder
WORKDIR /app
COPY /src ./src


RUN npm install
RUN npm run build

# 'npm install --omit=dev' does not prune test packages which are necessary
RUN npm install --omit=dev

FROM --platform="linux/arm64"  arm64v8/node@sha256:b16c4e21f9e9e4d02c226d7b2dde3283fc9315104b66009af546b50f5c7acad4 as final
#RUN addgroup -S appgroup && adduser -S appuser -G appgroup

RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "tini"]
USER appuser:appgroup

WORKDIR /app
# Copy in compile assets and deps from build container
COPY --chown=appuser:appgroup --from=builder /app/node_modules ./node_modules
COPY --chown=appuser:appgroup --from=builder /app/dist ./dist
COPY --chown=appuser:appgroup --from=builder /app/src ./src
COPY --chown=appuser:appgroup --from=builder /app/package.json ./
COPY --chown=appuser:appgroup --from=builder /app/package-lock.json ./

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["tini", "--"]

CMD ["npm", "start"]
