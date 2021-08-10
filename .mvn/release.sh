#!/usr/bin/env bash

current_script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"
echo "current script dir $current_script_dir"

maven_wrapper_dir="$(dirname "${current_script_dir}")"
echo "maven wrapper dir $maven_wrapper_dir"

current_version=$(./mvnw -q --non-recursive exec:exec -Dexec.executable=echo -Dexec.args='${project.version}')
echo "current version $current_version"

if [[ "$current_version" == *"SNAPSHOT"* ]]; then
  echo "releasing SNAPSHOT version using maven-release-plugin, example: a.b.c-SNAPSHOT -> a.b.c"
  ./mvnw release:clean release:prepare release:perform \
    -Dgoals="clean package" -DautoVersionSubmodules=true \
    -DgenerateReleasePoms=false -DgenerateBackupPoms=false -B
else
  echo "releasing non-SNAPSHOT version manually with build-helper, example: a.b.c -> a.b.c+1"
  root_artifact_id=$(./mvnw -q --non-recursive exec:exec -Dexec.executable=echo -Dexec.args='${project.artifactId}')
  tag_name="${root_artifact_id}-${current_version}"
  echo "tag name $tag_name"
  git tag $tag_name
  ./mvnw -B build-helper:parse-version \
    -DautoVersionSubmodules=true -DgenerateReleasePoms=false -DgenerateBackupPoms=false \
    versions:set \
    -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion}
  git add .
  git commit -am "$tag_name release."
  git push origin master --tags
fi
