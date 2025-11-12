#!/bin/bash
set -e
cd /root/task
echo "[INFO] Building Docker images and starting containers..."
docker-compose build

echo "[INFO] Starting container..."
docker-compose up -d

echo "[INFO] Waiting for application to become available..."
for i in {1..30}; do
  if curl -s http://localhost:8080/api/tasks >/dev/null 2>&1; then
    echo "[SUCCESS] Application is up and responding."
    break
  fi
  sleep 2
done

docker ps

docker logs task-tracker-app | head -n 10
