#!/usr/bin/env bash
project_name=admin-api
image_tag=$(jq ".kubernetes.deployments[] | select(.id==$project_name).version" ./staging/env.json)
zone=europe-west1-c
cluster=smartworkx-cluster
echo "deploy $image_tag"
gcloud auth activate-service-account --key-file /tmp/service-account.json
gcloud config set project smartworkx-173909
gcloud container clusters get-credentials --zone $zone $cluster
sed s/IMAGE_TAG/$image_tag/g; ./sources/kubernetes/kubernetes.tpl > ./deploy/kubernetes.yml
echo "Create yaml in $(pwd) $(cat ./kubernetes.yml)"
kubectl apply -f ./deploy/kubernetes.yml
