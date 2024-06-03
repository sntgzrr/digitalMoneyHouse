FROM quay.io/keycloak/keycloak:24.0.4

COPY keycloak-export /opt/keycloak/data/import

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--import-realm"]