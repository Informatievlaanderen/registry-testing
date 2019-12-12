@ECHO OFF

SET CONF="%cd%\conf"
SET USER="%cd%\user-files"
SET RESULTS="%cd%\results"

IF NOT EXIST %CONF% (
    echo "Could not find %CONF%"
    goto end
)

IF NOT EXIST %USER% (
    echo "Could not find %USER%"
    goto end
)

IF NOT EXIST %RESULTS% (
    echo "Creating %GATLING_RESULTS%"
    mkdir %GATLING_RESULTS%
)

SET IMAGE="denvazh/gatling:3.2.1"
docker pull %IMAGE%
docker run -it --rm -v %CONF%:/opt/gatling/conf -v %USER%:/opt/gatling/user-files -v %RESULTS%:/opt/gatling/results -e JAVA_OPTS="-Dapi_key=%API_KEY% -Dbase_url=%BASE_URL% -Dwarmup_url=%WARMUP_URL% -Dfeeder_prefix=/opt/gatling/user-files/resources/" %IMAGE%

:end