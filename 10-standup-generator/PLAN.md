# Plan — Standup Generator

## Goal
Single HTML file: input yesterday/today/blockers → generate formatted standup → copy to clipboard.

## Phase 1 — HTML Structure
1. Form: date field (auto-filled with today), textarea for Yesterday, textarea for Today, textarea for Blockers
2. Radio buttons: Slack | Teams | Email | Plain Text
3. Output textarea (readonly) + "Copy" button

## Phase 2 — Format Generators (pure JS)
4. `generateSlack(data)`:
   ```
   *Standup — 2026-07-22*
   *Yesterday:* ...
   *Today:* ...
   *Blockers:* ...
   ```
5. `generateTeams(data)` — HTML bold tags for Teams markdown
6. `generateEmail(data)` — formal subject line + indented body
7. `generatePlain(data)` — simple labeled text

## Phase 3 — Copy + UX
8. `navigator.clipboard.writeText()` on Copy button
9. Show "Copied!" toast for 2 seconds
10. Auto-generate output on any input change (live preview)

## Phase 4 — Templates (LocalStorage)
11. "Save as Template" button — saves current Yesterday/Today as JSON in `localStorage`
12. "Load Template" button — restores saved values
13. Supports one template slot (simple version)

## Verification
- Open `index.html` in Chrome/Edge/Firefox — verify no errors
- Fill fields → switch formats → verify correct output generated
- Click Copy → paste into Slack → verify formatting correct
- Save template → reload page → load template → fields restored
