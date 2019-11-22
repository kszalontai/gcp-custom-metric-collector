package io.github.kszalontai.gcp.metric;

import static java.util.Objects.requireNonNull;

import com.google.common.collect.Lists;
import java.util.List;

public final class Resource {

  private final String type;
  private final List<Label> labels;

  private Resource(ResourceBuilder builder) {
    requireNonNull(builder.type);
    this.type = builder.type;
    this.labels = builder.labels;
  }

  public static ResourceBuilder type(String type) {
    return new ResourceBuilder(type);
  }

  public String type() {
    return type;
  }

  public List<Label> labels() {
    return labels;
  }

  public static final class ResourceBuilder {

    private final String type;
    private final List<Label> labels = Lists.newArrayList();

    public ResourceBuilder(String type) {
      this.type = type;
    }

    public ResourceBuilder label(Label label) {
      this.labels.add(label);
      return this;
    }

    public Resource build() {
      return new Resource(this);
    }
  }
}
