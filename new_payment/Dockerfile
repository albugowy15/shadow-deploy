ARG RUST_VERSION=1.89
FROM rust:${RUST_VERSION} AS build
WORKDIR /app
COPY . /app
RUN cargo build --release

FROM gcr.io/distroless/cc-debian12
COPY --from=build /app/target/release/new_payment /

CMD ["./new_payment"]
