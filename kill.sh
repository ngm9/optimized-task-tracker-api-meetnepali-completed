#!/bin/bash
set -e

echo "[INFO] Stopping and removing Docker containers..."
docker-compose -f /root/task/docker-compose.yml down --volumes --remove-orphans || true

echo "[INFO] Removing Docker images..."
docker rmi -f $(docker images -q | grep -E 'task|openjdk') || true

echo "[INFO] Pruning Docker system..."
docker system prune -a --volumes -f

echo "[INFO] Removing task directory..."
rm -rf /root/task || true

echo "[INFO] Cleanup completed successfully! Droplet is now clean."
