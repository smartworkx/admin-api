#!/usr/bin/env bash
PROJECT_NAME=admin-api
IMAGE_TAG=$(jq '.kubernetes.deployments[] | select(.id==$PROJECT_NAME).version' ./staging/env.json)
ZONE=europe-west1-c
CLUSTER=smartworkx-cluster
echo "deploy $IMAGE_TAG"
gcloud auth activate-service-account --key-file /tmp/service-account.json
gcloud config set project smartworkx-173909
gcloud container clusters get-credentials --zone $ZONE $CLUSTER
sed s/IMAGE_TAG/$IMAGE_TAG/g; ./sources/kubernetes/kubernetes.tpl > ./deploy/kubernetes.yml
echo "Create yaml in $(pwd) $(cat ./kubernetes.yml)"
kubectl apply -f ./deploy/kubernetes.yml
