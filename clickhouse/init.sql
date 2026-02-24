CREATE DATABASE IF NOT EXISTS app;

CREATE TABLE IF NOT EXISTS app.event_counts (
  ts DateTime DEFAULT now(),
  cnt UInt64
) ENGINE = MergeTree
ORDER BY ts;