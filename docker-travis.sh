projectName=$(grep  rootProject.name settings.gradle | cut -d' ' -f3 | cut -d"'" -f2)

echo Building docker image for project $projectName
docker build -t $DOCKER_UN/$projectName .

echo Pushing docker image to repository
echo "$DOCKER_PW" | docker login -u "$DOCKER_UN" --password-stdin
docker push $DOCKER_UN/$projectName
