CREATE TABLE "APP".WB_SHORT_NEED (
    ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999,
    CreatedDate DATE NOT NULL,
    ReceiveTime TIMESTAMP NOT NULL,
    SecurityCode VARCHAR2(255) NOT NULL,
    SettlementDate VARCHAR2(255) NOT NULL,
    NeedType VARCHAR2(255) NOT NULL,
    RunTime VARCHAR2(10) NOT NULL,
    PartialFlag VARCHAR2(1) NOT NULL,
    IsNewVersion VARCHAR2(1) NOT NULL,
    Quantity NUMBER(20, 2) NOT NULL,
    DivStrategy VARCHAR2(255) NOT NULL,
    IsManual VARCHAR2(1) NOT NULL,
    CorrelationID VARCHAR2(255) NOT NULL,
    PTHQty NUMBER(20, 2),
    ETFQuantoQty NUMBER(20, 2),
    CollateralRecallQty NUMBER(20, 2),
    AdjustedQty NUMBER(20, 2),
    WashQty NUMBER(20, 2),
    PMID VARCHAR2(255),
    PRIMARY KEY (CorrelationID)
);

CREATE INDEX idx_WB_ShortNeed_CreatedDate_Time ON WB_SHORT_NEED (CreatedDate, ReceiveTime);
CREATE INDEX idx_WB_ShortNeed_ReceiveTime_Date ON WB_SHORT_NEED (ReceiveTime, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_Security_Settlement ON WB_SHORT_NEED (SecurityCode, SettlementDate);
CREATE INDEX idx_WB_ShortNeed_Settlement_Security ON WB_SHORT_NEED (SettlementDate, SecurityCode);
CREATE INDEX idx_WB_ShortNeed_NeedType_Date ON WB_SHORT_NEED (NeedType, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_DivStrategy_Date ON WB_SHORT_NEED (DivStrategy, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_IsManual_Date ON WB_SHORT_NEED (IsManual, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_Quantity_Covering ON WB_SHORT_NEED (Quantity, CreatedDate, SecurityCode, NeedType, DivStrategy);
CREATE INDEX idx_WB_ShortNeed_PTHQty_Covering ON WB_SHORT_NEED (PTHQty, CreatedDate, SecurityCode, NeedType);
CREATE INDEX idx_WB_ShortNeed_ETFQuantoQty_Covering ON WB_SHORT_NEED (ETFQuantoQty, CreatedDate, SecurityCode, NeedType);
CREATE INDEX idx_WB_ShortNeed_Security_Type_Date ON WB_SHORT_NEED (SecurityCode, NeedType, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_Settlement_Type_Date ON WB_SHORT_NEED (SettlementDate, NeedType, CreatedDate);
CREATE INDEX idx_WB_ShortNeed_CreatedDate_Trunc ON WB_SHORT_NEED (TRUNC(CreatedDate));
CREATE INDEX idx_WB_ShortNeed_ReceiveTime_Trunc ON WB_SHORT_NEED (TRUNC(ReceiveTime));

CREATE TABLE "APP".WB_ORDER_GROUP (
    OrderGroupID NUMBER NOT NULL,
    UserId VARCHAR2(255) NOT NULL,
    ButtonClickTime TIMESTAMP NOT NULL,
    NumberOfShortsSelected NUMBER NOT NULL,
    ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999,
    NumberOfMarketsSelected NUMBER NOT NULL,
    BidOption VARCHAR2(255) NOT NULL,
    PRIMARY KEY (OrderGroupID)
);

CREATE INDEX idx_WB_OrderGroup_UserId_Time ON WB_ORDER_GROUP (UserId, ButtonClickTime);
CREATE INDEX idx_WB_OrderGroup_Time_User ON WB_ORDER_GROUP (ButtonClickTime, UserId);
CREATE INDEX idx_WB_OrderGroup_BidOption_Time ON WB_ORDER_GROUP (BidOption, ButtonClickTime);
CREATE INDEX idx_WB_OrderGroup_Shorts_Markets ON WB_ORDER_GROUP (NumberOfShortsSelected, NumberOfMarketsSelected);

CREATE TABLE "APP".WB_ORDER (
    ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999,
    OrderID NUMBER NOT NULL,
    OrderGroupID NUMBER NOT NULL,
    CorrelationID VARCHAR2(255) NOT NULL,
    FillQuantity NUMBER(20, 2),
    PRIMARY KEY (OrderID),
    FOREIGN KEY (OrderGroupID) REFERENCES WB_ORDER_GROUP (OrderGroupID),
    FOREIGN KEY (CorrelationID) REFERENCES WB_SHORT_NEED (CorrelationID)
);

CREATE INDEX idx_WB_Order_OrderGroupID_CorrelationID ON WB_ORDER (OrderGroupID, CorrelationID);
CREATE INDEX idx_WB_Order_CorrelationID_GroupID ON WB_ORDER (CorrelationID, OrderGroupID);
CREATE INDEX idx_WB_Order_FillQuantity_Covering ON WB_ORDER (FillQuantity, OrderGroupID, CorrelationID);

CREATE TABLE "APP".WB_BID (
    ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999,
    BidID VARCHAR2(255) NOT NULL,
    BatchID VARCHAR2(255) NOT NULL,
    RequestSequence NUMBER,
    BidType VARCHAR2(255),
    NgtChainID NUMBER,
    EquilendID NUMBER,
    QuantityFilled NUMBER(20, 2),
    Status VARCHAR2(255),
    StatusDescription VARCHAR2(255),
    CptyLeID NUMBER NOT NULL,
    CptyCorpID NUMBER NOT NULL,
    LenderReferenceID VARCHAR2(255) NOT NULL,
    Rate NUMBER(20, 2),
    Fee NUMBER(20, 2),
    SubAcct VARCHAR2(255),
    ContractPrice NUMBER(20, 2),
    CollCashAmt NUMBER(20, 2),
    CollCcy VARCHAR2(255),
    CollType VARCHAR2(255) NOT NULL,
    CollDesc VARCHAR2(255),
    TraderName VARCHAR2(255) NOT NULL,
    StartTime TIMESTAMP NOT NULL,
    EndTime TIMESTAMP NOT NULL,
    AbSeqNbr NUMBER NOT NULL,
    DateStamp TIMESTAMP NOT NULL,
    OrderID NUMBER NOT NULL,
    OrderGroupID NUMBER NOT NULL,
    CorrelationID VARCHAR2(255) NOT NULL,
    RateIndicator VARCHAR2(10),
    PRIMARY KEY (BidID),
    FOREIGN KEY (OrderID) REFERENCES WB_ORDER (OrderID),
    FOREIGN KEY (OrderGroupID) REFERENCES WB_ORDER_GROUP (OrderGroupID),
    FOREIGN KEY (CorrelationID) REFERENCES WB_SHORT_NEED (CorrelationID)
);

CREATE INDEX idx_WB_BID_OrderID_CorrelationID ON WB_BID (OrderID, CorrelationID);
CREATE INDEX idx_WB_BID_CorrelationID_OrderID ON WB_BID (CorrelationID, OrderID);
CREATE INDEX idx_WB_BID_OrderGroupID_CorrelationID ON WB_BID (OrderGroupID, CorrelationID);
CREATE INDEX idx_WB_BID_Status_Time ON WB_BID (Status, StartTime, EndTime);
CREATE INDEX idx_WB_BID_TraderName_Time ON WB_BID (TraderName, StartTime);
CREATE INDEX idx_WB_BID_BidType_Status ON WB_BID (BidType, Status);
CREATE INDEX idx_WB_BID_Rate_Covering ON WB_BID (Rate, BidType, Status, TraderName);
CREATE INDEX idx_WB_BID_Fee_Covering ON WB_BID (Fee, BidType, Status, TraderName);
CREATE INDEX idx_WB_BID_QuantityFilled_Covering ON WB_BID (QuantityFilled, OrderID, CorrelationID);
CREATE INDEX idx_WB_BID_StartTime_EndTime ON WB_BID (StartTime, EndTime);
CREATE INDEX idx_WB_BID_DateStamp_Time ON WB_BID (DateStamp, StartTime);

CREATE TABLE "APP".WB_FILL (
    ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999,
    TicketID NUMBER NOT NULL,
    OrderID NUMBER NOT NULL,
    BidID VARCHAR2(255) NOT NULL,
    PRIMARY KEY (TicketID),
    FOREIGN KEY (OrderID) REFERENCES WB_ORDER (OrderID),
    FOREIGN KEY (BidID) REFERENCES WB_BID (BidID)
);

CREATE INDEX idx_WB_FILL_OrderID_BidID ON WB_FILL (OrderID, BidID);
CREATE INDEX idx_WB_FILL_BidID_OrderID ON WB_FILL (BidID, OrderID);

CREATE INDEX idx_WB_ShortNeed_CorrelationID_Covering ON WB_SHORT_NEED (CorrelationID, SecurityCode, NeedType, Quantity, CreatedDate);
CREATE INDEX idx_WB_Order_CorrelationID_Covering ON WB_ORDER (CorrelationID, OrderGroupID, FillQuantity);
CREATE INDEX idx_WB_BID_CorrelationID_Covering ON WB_BID (CorrelationID, OrderID, OrderGroupID, Status, Rate, Fee);

CREATE INDEX idx_WB_ShortNeed_CreatedDate_Partition ON WB_SHORT_NEED (CreatedDate, CorrelationID, SecurityCode);
CREATE INDEX idx_WB_BID_StartTime_Partition ON WB_BID (StartTime, BidID, OrderID, CorrelationID);

CREATE INDEX idx_WB_ShortNeed_Security_Date_Type_Qty ON WB_SHORT_NEED (SecurityCode, CreatedDate, NeedType, Quantity);
CREATE INDEX idx_WB_BID_Order_Correlation_Status ON WB_BID (OrderID, CorrelationID, Status, Rate);
CREATE INDEX idx_WB_Order_Group_Correlation_Fill ON WB_ORDER (OrderGroupID, CorrelationID, FillQuantity);

CREATE INDEX idx_WB_ShortNeed_CreatedDate_Year ON WB_SHORT_NEED (EXTRACT(YEAR FROM CreatedDate), EXTRACT(MONTH FROM CreatedDate));
CREATE INDEX idx_WB_BID_StartTime_Year ON WB_BID (EXTRACT(YEAR FROM StartTime), EXTRACT(MONTH FROM StartTime));
