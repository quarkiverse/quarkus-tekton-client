package io.quarkiverse.tektonclient.deployment.nativeimage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.jboss.jandex.DotName;

public class IgnorePackagePredicate implements Predicate<DotName> {

    private static final List<String> IGNORED_PACKAGES = Arrays.asList("javax.crypto.", "javax.net.",
            "javax.security.auth.");

    @Override
    public boolean test(DotName dotName) {
        String name = dotName.toString();
        for (String containerPackageName : IGNORED_PACKAGES) {
            if (name.startsWith(containerPackageName)) {
                return true;
            }
        }
        return false;
    }

}
