sudo docker run \
  --name PostgreSQL \
  --rm --detach \
  --env POSTGRES_PASSWORD=chamis \
  --env POSTGRES_USER=postgres \
  --env POSTGRES_DB=chamis \
  -p 5432:5432 \
  -d \
  postgres