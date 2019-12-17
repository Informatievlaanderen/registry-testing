  
#!/usr/bin/env bash
set -e

dotnet tool restore

if [ $# -eq 0 ]
then
  FAKE_ALLOW_NO_DEPENDENCIES=true dotnet fake build
else
  FAKE_ALLOW_NO_DEPENDENCIES=true dotnet fake build -t "$@"
fi