#GUIDE on how to start Marklogic server

1. Install docker on your machine
2. Download RMP installation for CentOs7 from [Marklogic official website](#https://developer.marklogic.com/products)
3. Copy RMP file to Docker folder and Modify line that contains comment above properly in 'Dockerfile'
4. Run command in this directory `docker build -t marklogic:8.05-preinitialized .` this will start building image for marklogic server
5. type in `docker images` you should see marklogic image in the list
6. Command `docker run -d --name=initial-install -p 8001:8001 marklogic:8.05-preinitialized` will start docker container for this docker image
7. Go to `localhost:8001` and configure marklogic server


###Guide also available at: [Marklogic official website](#http://developer.marklogic.com/blog/building-a-marklogic-docker-container)
