#!/usr/bin/env bash
IMAGE_TAG=$(jq '.kubernetes.deployments[] | select(.id=="admin-api").version' ./staging/env.json)
echo "deploy $IMAGE_TAG"
gcloud auth activate-service-account --key-file /tmp/service-account.json
gcloud config set project smartworkx-173909
gcloud container clusters get-credentials --zone europe-west1-c smartworkx-cluster
sed s/IMAGE_TAG/$IMAGE_TAG/g ./sources/kubernetes/kubernetes.template.yml > ./deploy/kubernetes.yml
echo "Create yaml in $(pwd) $(cat ./kubernetes.yml)"
kubectl apply -f ./deploy/kubernetes.yml
