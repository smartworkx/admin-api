apiVersion: v1
kind: Service
metadata:
  name: admin-api
  labels:
    app: admin-api
    visualize: "true"
    name: "admin-api"
spec:
  ports:
  - port: 8080
    targetPort: 8080
  selector:
    app: admin-api
  type: "LoadBalancer"
---
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: admin-api
spec:
  replicas: 1
  revisionHistoryLimit: 2
  minReadySeconds: 20
  template:
    metadata:
      labels:
        app: admin-api
        visualize: "true"
        name: "admin-api"
    spec:
      containers:
      - name: admin-api
        image: smartworkx/admin-api:IMAGE_TAG
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8080
