Test URL: http://localhost:8000/currency-exchange/from/USD/to/INR

Expected response: 
{
	"id": 100,
	"from": "USD",
	"to": "INR",
	"conversionMultiple": 65,
	"environment": "8000",
	"date": null
}

For distributed tracing I am using zipkin in a docker container. Command for running zipkin: docker run -p 9411:9411 openzipkin/zipkin:2.23

I had to add the following dependencies: 

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-observation</artifactId>
		</dependency>

		<!-- OPTION 1: Open Telemetry as Bridge (RECOMMENDED) -->
		<!-- Open Telemetry 
    	- Simplified Observability (metrics, logs, and
		traces) -->

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-otel</artifactId>
		</dependency>

		<dependency>
			<groupId>io.opentelemetry</groupId>
			<artifactId>opentelemetry-exporter-zipkin</artifactId>
		</dependency>
		
Then I had to add the following properties: 

management.tracing.sampling.probability=1.0
logging.pattern.level=%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]

To access zipkin: localhost:9411