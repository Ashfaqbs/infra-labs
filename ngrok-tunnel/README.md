# ngrok – Tunneling Local APIs to the External World

## 1. Create an ngrok Account

Create an account using the link below:

```
https://dashboard.ngrok.com/get-started/setup
```

---

## 2. Get the Auth Token (API Key)

After logging in, get your auth token from:

```
https://dashboard.ngrok.com/endpoints?sortBy=updatedAt&orderBy=desc
```

---

## 3. Download ngrok Image and Run the Container

We used the official **ngrok Docker image** to expose a local service.

### Docker command used:

```
C:\tmp\postgRest> docker run --rm -it ngrok/ngrok http host.docker.internal:3000 --authtoken=AUTHTOKEN
```

Docker pulls the image and starts ngrok:

```
1d80241c79c8: Pull complete
0af772b69e49: Pull complete
47a8a5373497: Pull complete
7951cc58747c: Pull complete
Digest: sha256:adf7e5f2833d40632bc1f5bb58fc005a4676d3859b69d175e5094360e6639908
```

Command execution:

```
C:\tmp\postgRest> docker run --rm -it ngrok/ngrok http host.docker.internal:3000 --authtoken=AUTHTOKEN
```

---

## 4. ngrok Runtime Output

After running the command, ngrok starts successfully and shows the tunnel details:

```
ngrok (Ctrl+C to quit)

Session Status                online
Account                       Ashfaq (Plan: Free)
Update                        update available (version 3.20.0, Ctrl-U to update)
Version                       3.19.1
Region                        India (in)
Web Interface                 http://0.0.0.0:4040
Forwarding                    https://25e5-152-59-242-11.ngrok-free.app -> http://host.docker.internal:3000

Connections                   ttl     opn     rt1     rt5     p50     p90
                              0       0       0.00    0.00    0.00    0.00
```

---

## 5. What We Achieved

* We exposed an API that was running locally on **port 3000** to the external world.
* The API we wanted to expose was **PostgREST**, running inside Docker on port **3000**.
* After running the ngrok command, we received a public URL:

  ```
  https://25e5-152-59-242-11.ngrok-free.app
  ```
* This URL can now be accessed **from anywhere** to reach the local API.

---
