#!/usr/bin/env bash
fly -t minikube set-pipeline --pipeline admin-api --config pipeline.yml -l credentials.yml