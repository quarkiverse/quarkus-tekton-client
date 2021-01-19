package io.quarkiverse.tektonclient.deployment;

import org.jboss.jandex.IndexView;

import io.quarkiverse.tektonclient.TektonClientProducer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class TektonClientProcessor {

    private static final String FEATURE = "quarkiverse-tekton-client";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void addDependencies(BuildProducer<IndexDependencyBuildItem> indexDependency) {
        indexDependency.produce(new IndexDependencyBuildItem("io.fabric8", "tekton-client"));
        indexDependency.produce(new IndexDependencyBuildItem("io.fabric8", "tekton-model-v1alpha1"));
        indexDependency.produce(new IndexDependencyBuildItem("io.fabric8", "tekton-model-v1beta1"));
        indexDependency.produce(new IndexDependencyBuildItem("io.fabric8", "knative-model"));
    }

    @BuildStep
    public void registerTektonReflections(BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndexBuildItem) {

        IndexView index = combinedIndexBuildItem.getIndex();

        String[] classes = index.getKnownClasses().stream()
                .filter(classInfo -> classInfo.name().toString().startsWith("io.fabric8.tekton.pipeline"))
                .map(Object::toString).toArray(String[]::new);

        reflectiveClass.produce(new ReflectiveClassBuildItem(true, true, classes));
    }

    @BuildStep
    public void registerBeans(BuildProducer<AdditionalBeanBuildItem> additionalBeanBuildItemProducer) {
        additionalBeanBuildItemProducer.produce(AdditionalBeanBuildItem.unremovableOf(TektonClientProducer.class));
    }
}
