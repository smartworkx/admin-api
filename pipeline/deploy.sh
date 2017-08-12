#!/usr/bin/env bash
ADMIN_WEB_IMAGE_TAG=$(jq '.kubernetes.deployments[] | select(.id=="admin-web").version' ./staging/env.json)
echo "deploy $ADMIN_WEB_IMAGE_TAG"
gcloud auth activate-service-account --key-file /tmp/service-account.json
gcloud config set project smartworkx-173909
gcloud container clusters get-credentials --zone europe-west1-c smartworkx-cluster
sed s/ADMIN_WEB_IMAGE_TAG/$ADMIN_WEB_IMAGE_TAG/g ./sources/kubernetes/admin-web.tpl > ./deploy/admin-web.yml
echo "Create yaml in $(pwd) $(cat ./admin-web.yml)"
kubectl apply -f ./deploy/admin-web.yml
