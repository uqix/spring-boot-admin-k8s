# spring-boot-admin-k8s

## Publish to Docker Hub

```sh
cd spring-boot-admin-k8s

image_name=uqix/spring-boot-admin-k8s:1.1.2
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
