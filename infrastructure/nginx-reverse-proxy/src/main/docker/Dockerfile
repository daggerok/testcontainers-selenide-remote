FROM nginx:1.20.1-alpine
#FROM nginx:stable-alpine
LABEL MAINTAINER='Maksim Kostromin https://github.com/daggerok'
HEALTHCHECK --start-period=5s --interval=5s --timeout=5s --retries=99 \
        CMD test "$(nginx -v 2>&1)" = "nginx version: nginx/1.20.1" || exit 1
ADD --chown=root ./src/main/docker/default.conf /etc/nginx/conf.d/default.conf
