- scaling
	- if serverless cloud
		- use autoscaling provided by mongodb atlas
	- else
		- mongodb on kubernetes with autoscaling
		- sharding - this will improve IOPS and thus speed

- optimize query
	- use indexing on registration number of the vehicle, assuming that there would not be any duplicates
	- use caching like redis
	
- reduce cost
	- reduce db calls by using cache like redis
	- if traffic is undpredictable, use serverless model as it will auto scale in, which will save costs
	- if traffic is predictible, use reserved instances of ec2 (if AWS)
	- purge old data which is legally accepted

- security
	- use authorizations like OAuth between customer and microservices
	- use 2 way TLS communication where ever possible (api gateway to microservices)
	- use private cloud network (eg. AWS VPC) for microservices deployment
	- use api gateway (Eg. apigee) to implement the following
		- sql injection prevention
		- throttling
		- rate limiting
		- spike arrest
	- use AWS WAF, for preventing
		- DDoS attacks

- privacy challenges
	- PII data
		- Eg. vehicle owner name can be considered as PII
		- should not expose PII data anywhere in the flow
		- should not log PII data
		
	
- Cloud tech stack
	- AWS
		- EKS: for microservices deployment on kubernetes
		- ECR: container registry (for securely storing container images)
		- Parameter Store: for storing secret credentials
		- EC2: for mongodb servers (shards and replicas)
		- SNS: for pub sub alerting end users by SMS/emails
		
	