# Blogging Platform API

https://roadmap.sh/projects/blogging-platform-api

RESTful API for blog posting (is made for readmap.sh project)

### Features
+ [X] Create a new blog post
+ [X] Update an existing blog post
+ [X] Delete an existing blog post
+ [X] Get a single post
+ [X] Get all posts
  + needs paging
+ [ ] Filter posts by a search term
  + [ ] Creation/update date
  + [X] Title // marked done because unneeded - titles already unique and i can't filter by them
  + [X] Inner content / title
  + [X] Category
+ [X] Add/Delete/Batch categories for a post


Uses Spring Boot (Web/Data JPA) + PostgreSQL container


### Quick-Start
1. Create .env file for configurations (example one in `docs/`)

```bash
  cp docs/example.env .env
```

2. Start containers (the postgres is needed)

```bash
podman-compose up
```

3. Pass the .env file into application
I use Intellij, so i just pass Environment Variables file through runner configurations