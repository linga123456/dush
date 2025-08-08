# Trading App API Collection - Postman

## Overview
This Postman collection provides comprehensive testing capabilities for the Spring Boot Trading Application APIs. The collection includes all CRUD operations and specialized queries for each entity with proper payload examples.

## Collection Structure

### WB Short Need APIs
Core entity for short needs with `CorrelationID` as primary key.

**Endpoints:**
- `POST /api/wb-short-needs` - Create new short need
- `GET /api/wb-short-needs/{correlationId}` - Get by correlation ID
- `GET /api/wb-short-needs` - Get all short needs
- `GET /api/wb-short-needs/security-code/{securityCode}` - Get by security code
- `GET /api/wb-short-needs/created-date/{createdDate}` - Get by created date
- `GET /api/wb-short-needs/settlement-date/{settlementDate}` - Get by settlement date
- `GET /api/wb-short-needs/need-type/{needType}` - Get by need type
- `PUT /api/wb-short-needs/{correlationId}` - Update short need
- `DELETE /api/wb-short-needs/{correlationId}` - Delete short need

### WB Order APIs
Order management with `OrderID` as primary key.

**Endpoints:**
- `POST /api/wb-orders` - Create new order
- `GET /api/wb-orders/order/{orderId}` - Get by order ID
- `GET /api/wb-orders` - Get all orders
- `GET /api/wb-orders/order-group/{orderGroupId}` - Get by order group ID
- `GET /api/wb-orders/correlation/{correlationId}` - Get by correlation ID
- `PUT /api/wb-orders/order/{orderId}` - Update order
- `DELETE /api/wb-orders/order/{orderId}` - Delete order

### WB Bid APIs
Bid management with `BidID` as primary key.

**Endpoints:**
- `POST /api/wb-bids` - Create new bid
- `GET /api/wb-bids/{id}` - Get by ID
- `GET /api/wb-bids/bid/{bidId}` - Get by bid ID
- `GET /api/wb-bids` - Get all bids
- `GET /api/wb-bids/order/{orderId}` - Get by order ID
- `GET /api/wb-bids/order-group/{orderGroupId}` - Get by order group ID
- `GET /api/wb-bids/batch/{batchId}` - Get by batch ID
- `GET /api/wb-bids/status/{status}` - Get by status
- `PUT /api/wb-bids/{id}` - Update bid
- `DELETE /api/wb-bids/{id}` - Delete bid

### WB Order Group APIs
Order group management with `OrderGroupID` as primary key.

**Endpoints:**
- `POST /api/wb-order-groups` - Create new order group
- `GET /api/wb-order-groups/{id}` - Get by ID
- `GET /api/wb-order-groups/order-group/{orderGroupId}` - Get by order group ID
- `GET /api/wb-order-groups` - Get all order groups
- `GET /api/wb-order-groups/user/{userId}` - Get by user ID
- `PUT /api/wb-order-groups/{id}` - Update order group
- `DELETE /api/wb-order-groups/{id}` - Delete order group

### WB Fill APIs
Fill management with `TicketID` as primary key.

**Endpoints:**
- `POST /api/wb-fills` - Create new fill
- `GET /api/wb-fills/{id}` - Get by ID
- `GET /api/wb-fills/ticket/{ticketId}` - Get by ticket ID
- `GET /api/wb-fills` - Get all fills
- `GET /api/wb-fills/order/{orderId}` - Get by order ID
- `GET /api/wb-fills/bid/{bidId}` - Get by bid ID
- `PUT /api/wb-fills/{id}` - Update fill
- `DELETE /api/wb-fills/{id}` - Delete fill

## Setup Instructions

### 1. Import Collection
1. Open Postman
2. Click "Import" button
3. Select the `Trading_App_API_Collection.json` file
4. The collection will be imported with all endpoints

### 2. Configure Environment Variables
The collection uses the following variables:

```json
{
  "baseUrl": "http://localhost:8080"
}
```

**To set up:**
1. Click on the collection name
2. Go to "Variables" tab
3. Set `baseUrl` to your application URL (default: `http://localhost:8080`)

### 3. Environment Setup (Optional)
Create different environments for different stages:

**Development:**
```json
{
  "baseUrl": "http://localhost:8080"
}
```

**Staging:**
```json
{
  "baseUrl": "http://staging-server:8080"
}
```

**Production:**
```json
{
  "baseUrl": "http://production-server:8080"
}
```

## Usage Examples

### Complete Workflow Example

#### Step 1: Create WB Short Need
```bash
POST {{baseUrl}}/api/wb-short-needs
Content-Type: application/json

{
  "correlationId": "CORR-2024-001",
  "createdDate": "2024-01-15",
  "receiveTime": "2024-01-15T09:30:00",
  "securityCode": "AAPL",
  "settlementDate": "2024-01-17",
  "needType": "SHORT",
  "runTime": "09:30",
  "partialFlag": "N",
  "isNewVersion": "Y",
  "quantity": 1000.50,
  "divStrategy": "EQUAL",
  "isManual": "N",
  "pthQty": 500.25,
  "etfQuantoQty": 200.75,
  "collateralRecallQty": 150.00,
  "adjustedQty": 950.25,
  "washQty": 50.00,
  "pmid": "PMID-2024-001"
}
```

Note: The `pmid` field is optional and can be null.

#### Step 2: Create WB Order Group
```bash
POST {{baseUrl}}/api/wb-order-groups
Content-Type: application/json

{
  "orderGroupId": 2001,
  "userId": "USER-001",
  "buttonClickTime": "2024-01-15T09:30:00",
  "numberOfShortsSelected": 5,
  "numberOfMarketsSelected": 3,
  "bidOption": "AUTO"
}
```

#### Step 3: Create WB Order
```bash
POST {{baseUrl}}/api/wb-orders
Content-Type: application/json

{
  "orderId": 1001,
  "orderGroupId": 2001,
  "correlationId": "CORR-2024-001",
  "fillQuantity": 500.25
}
```

#### Step 4: Create WB Bid
```bash
POST {{baseUrl}}/api/wb-bids
Content-Type: application/json

{
  "bidId": "BID-2024-001",
  "batchId": "BATCH-2024-001",
  "requestSequence": 1,
  "bidType": "LIMIT",
  "ngtChainId": 12345,
  "equilendId": 67890,
  "quantityFilled": 500.25,
  "status": "ACTIVE",
  "statusDescription": "Bid is active",
  "cptyLeId": 11111,
  "cptyCorpId": 22222,
  "lenderReferenceId": "LEND-REF-001",
  "rate": 2.50,
  "fee": 25.00,
  "subAcct": "SUB-001",
  "contractPrice": 150.75,
  "collCashAmt": 75000.00,
  "collCcy": "USD",
  "collType": "CASH",
  "collDesc": "Cash collateral",
  "traderName": "John Doe",
  "startTime": "2024-01-15T09:30:00",
  "endTime": "2024-01-15T16:00:00",
  "abSeqNbr": 1,
  "dateStamp": "2024-01-15T09:30:00",
  "orderId": 1001,
  "orderGroupId": 2001,
  "correlationId": "CORR-2024-001",
  "rateIndicator": "FIXED"
}
```

#### Step 5: Create WB Fill
```bash
POST {{baseUrl}}/api/wb-fills
Content-Type: application/json

{
  "ticketId": 3001,
  "orderId": 1001,
  "bidId": "BID-2024-001"
}
```

### Query Examples

#### Get All Short Needs for a Security
```bash
GET {{baseUrl}}/api/wb-short-needs/security-code/AAPL
```

#### Get Orders by Correlation ID
```bash
GET {{baseUrl}}/api/wb-orders/correlation/CORR-2024-001
```

#### Get Bids by Status
```bash
GET {{baseUrl}}/api/wb-bids/status/ACTIVE
```

#### Get Fills by Order ID
```bash
GET {{baseUrl}}/api/wb-fills/order/1001
```

## Testing Scenarios

### Functional Testing

#### 1. CRUD Operations
- Test all Create, Read, Update, Delete operations
- Verify proper HTTP status codes
- Check response payload structure

#### 2. Validation Testing
- Test with invalid data (missing required fields)
- Test with invalid data types
- Test with boundary values

#### 3. Business Logic Testing
- Test relationships between entities
- Test cascading operations
- Test business rules

### Security Testing

#### 1. Input Validation
- Test SQL injection attempts
- Test XSS attempts
- Test malformed JSON

#### 2. Authentication/Authorization
- Test with invalid tokens
- Test with expired tokens
- Test with insufficient permissions

### Performance Testing

#### 1. Load Testing
- Test with multiple concurrent requests
- Test with large datasets
- Test response times

#### 2. Stress Testing
- Test with maximum payload sizes
- Test with high request volumes
- Test system limits

## Response Examples

### Success Response (200/201)
```json
{
  "correlationId": "CORR-2024-001",
  "id": 1,
  "createdDate": "2024-01-15",
  "receiveTime": "2024-01-15T09:30:00",
  "securityCode": "AAPL",
  "settlementDate": "2024-01-17",
  "needType": "SHORT",
  "runTime": "09:30",
  "partialFlag": "N",
  "isNewVersion": "Y",
  "quantity": 1000.50,
  "divStrategy": "EQUAL",
  "isManual": "N",
  "pthQty": 500.25,
  "etfQuantoQty": 200.75,
  "collateralRecallQty": 150.00,
  "adjustedQty": 950.25,
  "washQty": 50.00,
  "pmid": "PMID-2024-001"
}
```

Note: The `pmid` field can be null if not provided during creation.

### Error Response (400/404/500)
```json
{
  "timestamp": "2024-01-15T09:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/wb-short-needs",
  "details": [
    {
      "field": "correlationId",
      "message": "Correlation ID is required"
    }
  ]
}
```

## Troubleshooting

### Common Issues

#### 1. Connection Refused
- Check if the application is running
- Verify the `baseUrl` variable
- Check firewall settings

#### 2. Validation Errors
- Review the request payload
- Check required fields
- Verify data types

#### 3. Authentication Errors
- Check if authentication is required
- Verify credentials
- Check token expiration

### Debugging Tips

#### 1. Enable Logging
- Set logging level to DEBUG in application.yml
- Check application logs for errors

#### 2. Use Postman Console
- Open Postman Console (View > Show Postman Console)
- Check request/response details

#### 3. Test Individual Endpoints
- Test endpoints one by one
- Verify each step before proceeding

## Best Practices

### Testing Strategy

#### 1. Test Data Management
- Use unique identifiers for test data
- Clean up test data after testing
- Use descriptive test data names

#### 2. Test Organization
- Group related tests together
- Use descriptive test names
- Add comments for complex scenarios

#### 3. Test Automation
- Use Postman's test scripts
- Automate repetitive tests
- Set up CI/CD integration

### Maintenance

#### 1. Regular Updates
- Update collection when APIs change
- Review and update test data
- Maintain documentation

#### 2. Version Control
- Version control the collection
- Track changes over time
- Document breaking changes

## Support

### Getting Help

#### 1. Documentation
- Check this README
- Review API documentation
- Check application logs

#### 2. Community
- Postman Community forums
- Stack Overflow
- GitHub issues

#### 3. Contact
- Development team
- System administrators
- Database administrators

## Conclusion

This Postman collection provides a comprehensive testing solution for the Trading Application APIs. It includes all necessary endpoints with proper payload examples and covers various testing scenarios. Regular maintenance and updates ensure the collection remains effective for testing purposes.
