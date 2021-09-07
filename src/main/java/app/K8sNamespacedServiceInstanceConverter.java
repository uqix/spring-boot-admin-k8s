package app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import de.codecentric.boot.admin.server.cloud.discovery.KubernetesServiceInstanceConverter;
import de.codecentric.boot.admin.server.domain.values.Registration;

@Component
@ConfigurationProperties(prefix = "spring.boot.admin.discovery.converter")
class K8sNamespacedServiceInstanceConverter extends KubernetesServiceInstanceConverter {

    @Override
    public Registration convert(final ServiceInstance instance) {
        return Registration.copyOf(super.convert(instance))
            .name(instance.getMetadata().get("k8s_namespace") + "/" + instance.getServiceId())
            .build();
    }
}
