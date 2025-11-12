# Task Overview
This Task Tracker API helps manage to-do tasks for a team. While it works, it suffers from API delays and unnecessary memory use as completed tasks accumulate without limit. Stats calculation on total tasks runs synchronously, slowing requests. The Docker setup produces a large image, builds slowly, and runs with unstable memory. Both Java and Docker require basic improvements to ensure smooth, efficient operation suitable for daily developer use.

# Helpful Tips
- Review how completed tasks are handled in memory and consider the impact on long-term stability.  
- Investigate how statistics are computed and explore techniques to prevent blocking or delays in API responses.  
- Evaluate opportunities to introduce background or asynchronous processing for heavy operations.  
- Inspect the container build process to identify unnecessary layers, files, or dependencies that increase image size.  
- Consider how a clean build context, organized Dockerfile structure, and runtime limits can enhance performance.  
- Use your understanding of resource management, memory allocation, and best practices for efficient containerized apps.

# Application Access
- API: `http://<DROPLET_IP>:8080/api/tasks`
- Example Endpoints:
  - `GET /api/tasks` — List all tasks
  - `POST /api/tasks` — Add new task (JSON)
  - `GET /api/tasks/stats` — Show current stats (runs slow)
- Common tools: curl, browser, Postman


# Objectives
- Analyze and address the performance issues related to memory usage and response latency.  
- Improve how the application handles completed or long-lived tasks to prevent unnecessary resource growth.  
- Introduce efficient execution patterns to ensure heavy operations don’t block main user requests.  
- Streamline the Docker build process to reduce image size and build time through better layering and context management.  
- Implement practical optimizations to stabilize container runtime behavior and manage available memory effectively.  
- Ensure the overall setup demonstrates sound software engineering and containerization principles.

# How to Verify
- API endpoints (`/api/tasks`, `/api/tasks/stats`) respond consistently faster under repeated requests.  
- The application maintains stable memory usage even as tasks increase or complete over time.  
- The statistics endpoint updates efficiently without blocking the main application flow.  
- Docker image size and build time show noticeable improvements.  
- `.dockerignore` and structured build steps minimize unnecessary files and layers.  
- `docker stats` reflects controlled and steady resource consumption during execution.
