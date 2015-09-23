FROM quay.io/pharmpress/nexus

RUN mkdir -p /sonatype-work/plugin-repository
ADD target/distro/sonatype-work/nexus/plugin-repository/plugin-bundle.zip /sonatype-work/plugin-repository/plugin-bundle.zip
RUN unzip -d /sonatype-work/plugin-repository/  /sonatype-work/plugin-repository/plugin-bundle.zip \
    && rm /sonatype-work/plugin-repository/plugin-bundle.zip \
    && chown -R nexus:nexus /sonatype-work \
    && chmod o-w -R /sonatype-work

