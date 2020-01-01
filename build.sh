#!/usr/bin/env bash
set -e

shopt -s globstar

dotnet tool restore

dos2unix -q **/*.scala

if [ $# -eq 0 ]
then
  FAKE_ALLOW_NO_DEPENDENCIES=true dotnet fake build
else
  FAKE_ALLOW_NO_DEPENDENCIES=true dotnet fake build -t "$@"
fi

dos2unix -v flood-packages/**/*.scala