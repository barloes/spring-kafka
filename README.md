
streams-plaintext-input
streams-wordcount-output

kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic input-topic


kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic output-topic


kafka-console-producer --bootstrap-server localhost:9092 \
                            --topic input-topic

kafka-console-consumer --topic output-topic --from-beginning \
                             --bootstrap-server localhost:9092
