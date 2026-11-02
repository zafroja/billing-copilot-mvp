# Billing Copilot MVP 0.5

Read-only, evidence-backed revenue QA for field-service billing.

## v0.5 end-to-end flow
1. Upload a PDF contract.
2. Extract simple billing rules locally with PDFBox + deterministic extraction.
3. Persist the contract and extracted rules.
4. Human approves/rejects every rule; unapproved rules cannot affect an audit.
5. Run work/invoice data through the reconciliation engine using approved contract rules only.
6. Review quantity, rate and missing-item discrepancies with potential dollar impact.

## New endpoints
- `POST /api/contracts/upload?tenantId=1` — extract + persist a contract.
- `GET /api/contracts/{contractId}/rules` — reviewable extracted rules.
- `PATCH /api/contracts/rules/{ruleId}` body `{"status":"APPROVED"}` or `REJECTED`.
- `POST /api/audits/contracts/{contractId}` — audit using APPROVED rules only.

## Demo contract clauses
- `Salt application: $130 per application`
- `Emergency cleanup: $165 per hour`
- `Monthly maintenance: $1400 per month`

## Run
1. `docker compose up -d`
2. `cd backend && mvn spring-boot:run`
3. `cd frontend && npm install && npm run dev`

## Safety rule
Read-only. No invoice is edited or sent. Extracted financial rules must be human-approved before reconciliation.

## Still intentionally deferred
- Real Jobber OAuth credentials / live Jobber data.
- AI provider credentials and production retention/privacy settings.
- Authentication/tenant authorization hardening.
