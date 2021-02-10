# k8s-spring-boot-admin

## Publish to Docker Hub

```sh
cd k8s-spring-boot-admin

image_name=uqix/k8s-spring-boot-admin:1.0.0
pack_builder=gcr.io/paketo-buildpacks/builder:base-platform-api-0.3

# Set proxy for pack on error of pulling from gcr.io
#https_proxy=http://localhost:7890

mvn clean package -DskipTests \
  && pack build $image_name \
       --builder $pack_builder \
       --volume ${PWD}/paketo/dependency-mapping:/platform/bindings/dependency-mapping \
       --pull-policy if-not-present \
       --path target/*.jar \
  && docker push $image_name
```
