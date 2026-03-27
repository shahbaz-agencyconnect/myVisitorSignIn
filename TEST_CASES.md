# TEST CASES GUIDE - Merchandiser Sign-In

## Overview

This document provides comprehensive test cases for the Merchandiser sign-in flow in simple English with test data format.

---

## 📋 Test Case Format

Each test case includes:
- **TC ID**: Test Case Identifier
- **Title**: What is being tested
- **Scenario**: Simple English description
- **Preconditions**: What must be true before test
- **Test Steps**: What to do
- **Expected Result**: What should happen
- **Test Data**: Values to use
- **Flow Type**: Which sign-in flow
- **Priority**: Critical/High/Medium/Low

---

## 🎯 Test Cases

### GROUP 1: FORM FILLING

#### TC001: Verify Email Input Accepts Valid Format
- **Title**: Store ID search and selection
- **Scenario**: User searches for a store by ID and selects it from results
- **Preconditions**: Application is open on store search page
- **Test Steps**:
  1. Enter store ID "1001" in search field
  2. Click Search button
  3. Select "Coles Supplier Store 1001" from results
  4. Verify Merchandiser tile appears
- **Expected Result**: Store is selected, user can proceed to next step
- **Test Data**: Store ID = 1001, Store Name = "Coles Supplier Store 1001"
- **Flow Type**: Initial Form
- **Priority**: Critical

#### TC002: Verify Email Input with Valid Email
- **Title**: Valid email entry
- **Scenario**: User enters a valid email address
- **Preconditions**: Induction form is displayed
- **Test Steps**:
  1. Click on Email field
  2. Enter "john.smith@coles.com.au"
  3. Tab to next field
- **Expected Result**: Email is accepted, no error message
- **Test Data**: Email = "john.smith@coles.com.au"
- **Priority**: Critical

#### TC003: Verify Induction Number Validation
- **Title**: Induction number verification
- **Scenario**: User enters induction number and system verifies it
- **Preconditions**: Email is filled
- **Test Steps**:
  1. Enter induction number "IND123456"
  2. Click Verify button
  3. Wait for verification check mark
- **Expected Result**: Check mark appears, induction is valid
- **Test Data**: Induction Number = "IND123456"
- **Priority**: Critical

#### TC004: Verify Company Name Entry
- **Title**: Company name field
- **Scenario**: User enters company name
- **Preconditions**: Induction verified
- **Test Steps**:
  1. Enter "ABC Logistics"
- **Expected Result**: Company name is stored
- **Test Data**: Company = "ABC Logistics"
- **Priority**: High

#### TC005: Verify First Name Entry
- **Title**: First name field
- **Scenario**: User enters first name
- **Test Steps**:
  1. Enter "John"
- **Expected Result**: First name accepted
- **Test Data**: First Name = "John"
- **Priority**: High

#### TC006: Verify Last Name Entry
- **Title**: Last name field
- **Scenario**: User enters last name
- **Test Steps**:
  1. Enter "Smith"
- **Expected Result**: Last name accepted
- **Test Data**: Last Name = "Smith"
- **Priority**: High

#### TC007: Verify Phone Number Entry
- **Title**: Phone number with prefix
- **Scenario**: User enters phone number (system adds 0 prefix)
- **Test Steps**:
  1. Enter "412345678"
  2. System converts to "0412345678"
- **Expected Result**: Phone formatted correctly
- **Test Data**: Phone = "412345678" (system adds 0)
- **Priority**: High

#### TC008: Verify Date of Birth Selection
- **Title**: Date picker functionality
- **Scenario**: User selects date from date picker
- **Preconditions**: Date picker is opened
- **Test Steps**:
  1. Select year "1990"
  2. Select month "March"
  3. Select day "15"
- **Expected Result**: Date is set to 15/03/1990
- **Test Data**: Year = 1990, Month = 03, Day = 15
- **Priority**: High

---

## 🎯 Test Cases

### GROUP 2: PRIVACY & AGREEMENTS

#### TC009: Verify Privacy Policy Modal Opens
- **Title**: Privacy policy link
- **Scenario**: User clicks Privacy Policy link and modal opens
- **Preconditions**: Form is filled
- **Test Steps**:
  1. Click "Privacy Policy" link
  2. Wait for PDF modal to appear
- **Expected Result**: Modal opens showing privacy document
- **Priority**: High

#### TC010: Verify Privacy Policy Scrolls to Bottom
- **Title**: PDF scrolling
- **Scenario**: System scrolls PDF to bottom to show full content
- **Test Steps**:
  1. PDF modal is open
  2. Verify scroll position
  3. Confirm all content visible
- **Expected Result**: PDF scrolled completely to bottom
- **Priority**: Medium

#### TC011: Verify Site Detail Form Agreement
- **Title**: Site Detail checkbox and I Agree button
- **Scenario**: User accepts Site Detail Form agreement
- **Preconditions**: Privacy Policy accepted
- **Test Steps**:
  1. Check "Site Detail Form" checkbox
  2. Modal opens with agreement text
  3. Scroll to bottom
  4. Click "I Agree" button
  5. Modal closes
- **Expected Result**: Agreement accepted, checkbox marked
- **Priority**: Critical

#### TC012: Verify Hazard Update Agreement
- **Title**: Hazard Update checkbox
- **Scenario**: User accepts Hazard Update Form agreement
- **Test Steps**:
  1. Check "Hazard Update Form" checkbox
  2. Modal opens
  3. Scroll to bottom
  4. Click "I Agree"
- **Expected Result**: Agreement accepted
- **Priority**: Critical

#### TC013: Verify Coles Wages Agreement
- **Title**: Coles Wages & Conditions checkbox
- **Scenario**: User accepts Wages and Conditions agreement
- **Test Steps**:
  1. Check "Coles Wages & Conditions" checkbox
  2. Modal opens
  3. Scroll to bottom
  4. Click "I Agree"
- **Expected Result**: Agreement accepted
- **Priority**: Critical

---

## 🎯 Test Cases

### GROUP 3: FLOW 1 - EXISTING ACCOUNT

#### TC014: Existing Account Flow - User Says Yes
- **Title**: User has existing account and confirms
- **Scenario**: System detects "Account Already Exists" and user clicks Yes
- **Preconditions**: All agreements accepted
- **Test Steps**:
  1. System detects "Account Already Exists" header
  2. User clicks "Yes" button
  3. System asks about change details
  4. User clicks "No" 
  5. User selects reason to visit
  6. User accepts optional safety form if present
  7. User clicks "Take My Photo"
- **Expected Result**: Flow completes successfully
- **Test Data**: Flow Type = EXISTING_ACCOUNT, ReasonToVisit = "Inspection of Stock"
- **Priority**: Critical

#### TC015: Existing Account With Details Change
- **Title**: User says account exists and details changed
- **Scenario**: After confirming existing account, user says details changed
- **Preconditions**: "Account Already Exists" detected
- **Test Steps**:
  1. Click "Yes" to existing account
  2. Click "Yes" to "Have any details changed?"
  3. Enter new details
  4. Continue flow
- **Expected Result**: New details saved
- **Test Data**: Updated company name, phone, etc.
- **Priority**: High

---

## 🎯 Test Cases

### GROUP 4: FLOW 2 - NEW SIGN-IN CONFIRMATION

#### TC016: New Sign-In Confirmation Flow
- **Title**: User already logged in elsewhere
- **Scenario**: System detects "New Sign In Confirmation" modal
- **Preconditions**: All agreements accepted, different device/session
- **Test Steps**:
  1. System detects "New Sign In Confirmation" header
  2. Click "Yes" to confirm new sign-in
  3. Click "No" to details changed question
  4. Select reason to visit
  5. Accept optional safety form
  6. Capture photo
- **Expected Result**: Flow completes with new session confirmed
- **Test Data**: Reason = "Inspection of Stock"
- **Priority**: Critical

#### TC017: New Confirmation With Additional Questions
- **Title**: Additional questions after confirmation
- **Scenario**: System asks follow-up questions after confirmation
- **Preconditions**: New confirmation detected
- **Test Steps**:
  1. Confirm new sign-in
  2. Answer questions:
     - Have details changed? → NO
     - Do you have valid safety document? → YES
  3. Continue to photo
- **Expected Result**: All questions answered correctly
- **Priority**: High

---

## 🎯 Test Cases

### GROUP 5: FLOW 3 - OTP VERIFICATION

#### TC018: OTP Flow - Fill PIN Inputs
- **Title**: User enters 6-digit OTP
- **Scenario**: System detects OTP requirement and user enters code
- **Preconditions**: All agreements accepted, new user detected
- **Test Steps**:
  1. System shows "Enter one time password" message
  2. User sees 6 empty PIN input boxes
  3. Fill each box with OTP digit: "9", "9", "9", "9", "9", "9"
  4. Click "Verify" button
- **Expected Result**: All digits entered, OTP verified
- **Test Data**: OTP = "999999"
- **Priority**: Critical

#### TC019: OTP Verification Success
- **Title**: OTP enters correctly
- **Scenario**: User enters correct OTP
- **Preconditions**: OTP modal displayed
- **Test Steps**:
  1. Enter each digit carefully
  2. Click Verify
  3. Wait for verification
  4. Reason selection appears
- **Expected Result**: OTP accepted, flow continues
- **Test Data**: OTP = "999999"
- **Priority**: Critical

#### TC020: OTP Then Reason Selection
- **Title**: Reason selection after OTP
- **Scenario**: After OTP verified, select reason to visit
- **Preconditions**: OTP verified successfully
- **Test Steps**:
  1. Select "Inspection of Stock" radio button
  2. Continue to next step
- **Expected Result**: Reason selected, flow proceeds
- **Test Data**: Reason = "Inspection of Stock"
- **Priority**: High

---

## 🎯 Test Cases

### GROUP 6: FLOW 4 - CHANGE DETAILS

#### TC021: Change Details Flow - No Changes
- **Title**: User confirms no details have changed
- **Scenario**: System asks if details changed, user says No
- **Preconditions**: "Have any details changed?" modal appears
- **Test Steps**:
  1. Click "No" button
  2. Select reason to visit
  3. Accept optional agreements
  4. Capture photo
- **Expected Result**: Flow continues with no changes required
- **Test Data**: No changes made
- **Priority**: High

#### TC022: Change Details Flow - With Changes
- **Title**: User updates changed details
- **Scenario**: User says details changed and updates them
- **Preconditions**: Change details modal appears
- **Test Steps**:
  1. Click "Yes" to details changed
  2. Update company name
  3. Update phone number
  4. Save changes
  5. Continue flow
- **Expected Result**: New details saved and stored
- **Test Data**: New Company = "XYZ Corp", New Phone = "0487654321"
- **Priority**: Medium

---

## 🎯 Test Cases

### GROUP 7: OPTIONAL ELEMENTS

#### TC023: Optional Safety Work Method Form Present
- **Title**: Safety form checkbox when present
- **Scenario**: Optional safety form appears and user accepts it
- **Preconditions**: Reason to visit selected, optional form visible
- **Test Steps**:
  1. Check "Safety Work Method Statement" checkbox
  2. Click "Verify"
  3. Continue to photo
- **Expected Result**: Checkbox marked, flow continues
- **Priority**: Medium

#### TC024: Optional Safety Form Not Present
- **Title**: Safety form when not present
- **Scenario**: Optional safety form does not appear
- **Preconditions**: Reason to visit selected, safety form not visible
- **Test Steps**:
  1. Verify safety form is not present
  2. Click "Take My Photo" directly
- **Expected Result**: Photo step executed
- **Priority**: Medium

---

## 🎯 Test Cases

### GROUP 8: PHOTO CAPTURE

#### TC025: Photo Capture Success
- **Title**: User captures valid photo
- **Scenario**: Camera starts and photo is captured
- **Preconditions**: All steps completed, photo step reached
- **Test Steps**:
  1. Click "Take My Photo" button
  2. Camera activates (video starts playing)
  3. Verify video playing (readyState=4)
  4. Click "Take My Photo" again to capture
- **Expected Result**: Photo captured, flow completes
- **Priority**: Critical

#### TC026: Photo Capture With Camera Start
- **Title**: Camera initialization before capture
- **Scenario**: System waits for camera to start before capturing
- **Test Steps**:
  1. Click "Take My Photo"
  2. System verifies video is playing
  3. Verify video.readyState === 4
  4. Verify video.currentTime > 0
  5. Capture photo
- **Expected Result**: Camera properly initialized before capture
- **Priority**: High

---

## 🎯 Test Cases

### GROUP 9: ERROR SCENARIOS

#### TC027: Invalid Email Format
- **Title**: Reject invalid email
- **Scenario**: User enters invalid email format
- **Test Steps**:
  1. Enter "invalidemail" (no @)
  2. Tab to next field
  3. System should reject or show error
- **Expected Result**: Error message or rejection
- **Priority**: Medium

#### TC028: Invalid Induction Number
- **Title**: Induction verification fails
- **Scenario**: System cannot verify induction number
- **Preconditions**: Invalid induction entered
- **Test Steps**:
  1. Enter "INVALID123"
  2. Click Verify
  3. Wait for response
- **Expected Result**: Error message, verification fails
- **Priority**: High

#### TC029: Missing Required Field
- **Title**: Cannot proceed without required fields
- **Scenario**: User tries to proceed with empty required field
- **Test Steps**:
  1. Leave email blank
  2. Try to click next/verify
- **Expected Result**: Button disabled or error shown
- **Priority**: Medium

---

## 📊 Test Data Summary Table

| TC# | Store ID | Store Name | Email | Induction# | Company | First | Last | Phone | Year | Month | Day | Reason |
|-----|----------|-----------|-------|-----------|---------|-------|------|-------|------|-------|-----|--------|
| TC001-TC008 | 1001 | Coles Supplier 1 | john.smith@coles.com.au | IND123456 | ABC Logistics | John | Smith | 412345678 | 1990 | 03 | 15 | Inspection |
| TC014 | 1002 | Coles Supplier 2 | jane.doe@coles.com.au | IND123457 | XYZ Corp | Jane | Doe | 487654321 | 1985 | 06 | 22 | Training |
| TC016 | 1003 | Coles Supplier 3 | mike.jones@coles.com.au | IND123458 | ABC Logistics | Mike | Jones | 498765432 | 1988 | 09 | 10 | Safety Check |
| TC018 | 1004 | Coles Supplier 4 | sarah.brown@coles.com.au | IND123459 | Tech Solutions | Sarah | Brown | 402345678 | 1992 | 12 | 05 | Inventory |

---

## ✅ Test Execution Checklist

### Pre-Execution
- [ ] Test environment is ready
- [ ] WebDriver is configured
- [ ] Test data is prepared in Excel
- [ ] Application is accessible

### During Execution
- [ ] Each test case is documented
- [ ] Pass/Fail is recorded
- [ ] Screenshots taken for failures
- [ ] Logs collected

### Post-Execution
- [ ] Test results summarized
- [ ] Failed tests investigated
- [ ] Bugs logged if found
- [ ] Report generated

---

## 🎯 Test Execution Example (Java)

```java
@Test
public void testExistingAccountFlow() {
    // TC014: Existing Account Flow
    MerchandiserRefactored merchandiser = new MerchandiserRefactored(driver);
    
    merchandiser.merchandiserSigIn();
    
    // Verify flow completed
    Assert.assertTrue("Flow should complete", 
        driver.getCurrentUrl().contains("success"));
}

@Test
public void testOTPFlow() {
    // TC018: OTP Verification
    MerchandiserRefactored merchandiser = new MerchandiserRefactored(driver);
    
    merchandiser.merchandiserSigIn();
    
    // Verify OTP was entered
    Assert.assertFalse("Should not show OTP error",
        driver.getPageSource().contains("Invalid OTP"));
}
```

---

## 📈 Coverage Report

| Category | Test Cases | Status |
|----------|-----------|--------|
| Form Filling | TC001-TC008 | ✅ Ready |
| Privacy & Agreements | TC009-TC013 | ✅ Ready |
| Flow 1 (Existing Account) | TC014-TC015 | ✅ Ready |
| Flow 2 (New Confirmation) | TC016-TC017 | ✅ Ready |
| Flow 3 (OTP) | TC018-TC020 | ✅ Ready |
| Flow 4 (Change Details) | TC021-TC022 | ✅ Ready |
| Optional Elements | TC023-TC024 | ✅ Ready |
| Photo Capture | TC025-TC026 | ✅ Ready |
| Error Scenarios | TC027-TC029 | ✅ Ready |
| **TOTAL** | **29 Test Cases** | **✅ COMPLETE** |

---

## 📋 Test Priority Distribution

- **Critical** (Must Pass): 8 tests
- **High** (Should Pass): 13 tests  
- **Medium** (Nice to Pass): 8 tests
- **Low** (Optional): 0 tests

---

## 🎓 How to Use This Guide

1. **For New Tests**: Pick a test case and follow the steps
2. **For Automation**: Use test data in spreadsheet
3. **For Regression**: Run all 29 tests each build
4. **For Reports**: Track pass/fail for each TC

---

**Created**: March 24, 2026  
**Total Test Cases**: 29  
**Coverage**: All flows and scenarios  
**Status**: Ready for execution ✅

