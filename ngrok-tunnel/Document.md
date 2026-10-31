# Ngrok Backend Tunneling – Quick Reference

This document explains **how we exposed a local backend API to the internet using ngrok**, step by step. Use this as a checklist whenever you need to do it again.

---

## 1. What We Were Trying to Do

Expose a **local backend API** running on your machine so it can be accessed from:

* Another machine
* Postman
* Browser
* Webhooks (Razorpay, Stripe, etc.)

Example local API:

```
http://localhost:8081/api/v1/payment/health
```

Response:

```
Payment service is running!
```

---

## 2. Prerequisites

* Backend service running locally
* Port known (example: `8081`)
* ngrok installed and authenticated

Check ngrok login:

```
ngrok config check
```

---

## 3. Step 1 – Verify Backend Locally (MOST IMPORTANT)

Before ngrok, always confirm the API works locally.

### Browser or curl test

```
curl http://localhost:8081/api/v1/payment/health
```

Expected:

```
Payment service is running!
```

If this fails → **stop here and fix backend first**.

---

## 4. Step 2 – Start ngrok Tunnel

Run ngrok for the same port as your backend:

```
ngrok http 8081
```

ngrok output will show:

```
Forwarding
https://<random>.ngrok-free.app -> http://localhost:8081
```

This means:

```
Internet → ngrok URL → your machine → port 8081
```

---

## 5. Step 3 – Access API via ngrok URL

Use the ngrok public URL **with the same API path**:

```
https://<random>.ngrok-free.app/api/v1/payment/health
```

Expected response:

```
Payment service is running!
```

✅ If you see this → tunneling is working

---

## 6. Step 4 – Inspect Traffic Using ngrok Dashboard

ngrok provides a local web UI to debug requests.

Open in browser:

```
http://localhost:4040
```

You can see:

* Incoming requests
* Paths hit (`/api/v1/payment/health`)
* Status codes (200 / 404 / 502)
* Response body

This is extremely useful for debugging.

---

## 7. Common Errors & Fixes

### 502 Bad Gateway

**Meaning:** ngrok cannot reach your backend

Causes:

* Backend not running
* Wrong port
* Docker container not exposing port

Fix:

```
ngrok http <correct-port>
```

---

### 404 Not Found

**Meaning:** backend reached, but API path is wrong

Fix:

* Double-check endpoint path

---

### Connection Refused (Docker apps)

**Meaning:** app bound to `localhost`

Fix: backend must bind to:

```
0.0.0.0
```

---

## 8. When to Use ngrok

Use ngrok when you need:

* Webhook testing (Razorpay, Stripe)
* External API callbacks
* Demoing local backend
* Temporary public access

Do NOT use ngrok as a production deployment.

---

## 9. Quick Checklist (Before Sharing URL)

* [ ] Backend running
* [ ] Local API works
* [ ] Correct port used in ngrok
* [ ] ngrok URL tested with full path
* [ ] Checked requests in `localhost:4040`

---

## 10. Example Command Summary

```
# Start backend
# Verify locally
curl http://localhost:8081/api/v1/payment/health

# Start ngrok
ngrok http 8081

# Test public access
https://<random>.ngrok-free.app/api/v1/payment/health
```

---

## 11. Key Takeaway

> **If localhost doesn’t work, ngrok will ne
