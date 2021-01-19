# Quarkiverse Tekton Client!
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

This Quarkus extension exposes the [Fabric8](https://github.com/fabric8io/kubernetes-client) [Tekton](https://tekton.dev/) client.

Main use of this extension is to be able to inject the Tekton client without worrying about the
complexity related to handling dependencies in case of **native builds**.

## Coordinates

```xml
<dependency>
    <groupId>io.quarkiverse.tektonclient</groupId>
    <artifactId>quarkus-tekton-client</artifactId>
    <version>LATEST</version>
</dependency>
```

## Usage

```java
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import io.fabric8.tekton.client.TektonClient;
import io.fabric8.tekton.pipeline.v1beta1.Pipeline;


@ApplicationScoped
public class TektonResourceClient {

    @Inject
    TektonClient tektonClient;

    public List<Pipeline> listPipelines() {
        return tektonClient.v1beta1().pipelines().list().getItems();
    }

}
```

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://goldmann.pl/"><img src="https://avatars2.githubusercontent.com/u/43489?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Marek Goldmann</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkiverse-tekton-client/commits?author=goldmann" title="Code">💻</a> <a href="#maintenance-goldmann" title="Maintenance">🚧</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
