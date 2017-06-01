```
title: Browserify in a Docker
id: Browserify
tag:
	-tag1
	-tag2
toc: 2
```

# Browserify in a docker

## Create the image
Dockerfile to use
```sh
FROM node

RUN npm install
RUN npm install -g browserify

RUN npm install uniq --save
...
RUN npm install <your-dependencies> --save
```

The run
```
docker build -t browserify-i .
```

## Run the image

You can bundles all required libraries in one bundle file. This allow to separate your script from provided libraries .To create a library file which will contain all the needed libraries execute the next line.
```sh
docker run \
       -it \
       --rm \
       -v "$PWD":/browserify \
       --name browserify-container \
       browserify-i \
       browserify -r uniq -r <your-dependencies> > /browserify/lib.js
```
This line is executed only when libraries change (update or by adding or removing libs). Of course you can create a file per library.

Then to bundle you code using the external library execute the next line.
```sh
docker run \
       -it \
       --rm \
       -v "$PWD":/browserify \
       --name browserify-container \
       browserify-i \
       browserify --no-bundle-external /browserify/main.js -o /browserify/bundle.js
```

To embbed dependencies in the bundle file execute : 
```sh
docker run \
       -it \
       --rm \
       -v "$PWD":/browserify \
       --name browserify-container \
       browserify-i \
       browserify /browserify/main.js -o /browserify/bundle.js
```
