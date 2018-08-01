#!/usr/bin/env bash
fly -t main set-pipeline --pipeline admin-api --config pipeline.yml -l credentials.yml