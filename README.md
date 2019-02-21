# Sample Micro Service Framework

## What covered

* Jackson configuration to support JSON *
* Custom exception (Base exception from Business, System, Persistence, DataFormat and HTTP exceptions) *
* PCF support *
* Environment support for application.properties *
* Server name, port customization *
* Build and API version info *
* HTTPS csrf *
* Cache controller configuration *
* Object mapper *
* Rest controllers *
* Service layer for business logic implementation *
* Validation for the request entity *

## Jackson - Configuration

    Jackson is configured for below currently.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.registerModule(customSerializersModule());
        mapper.registerModule(new JodaModule());
