    plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
rootProject.name = "books"
include("app", "domain", "utilities", ":domain-implementation:file", ":domain-implementation:jdbc", ":domain-implementation:jpa","server")