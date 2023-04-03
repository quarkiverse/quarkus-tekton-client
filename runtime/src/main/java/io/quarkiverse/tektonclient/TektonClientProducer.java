package io.quarkiverse.tektonclient;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.tekton.client.DefaultTektonClient;
import io.fabric8.tekton.client.TektonClient;
import io.quarkus.arc.DefaultBean;

@Singleton
public class TektonClientProducer {

    @DefaultBean
    @Singleton
    @Produces
    public TektonClient tektonClient(Config config) {
        return new DefaultTektonClient(config);
    }
}
