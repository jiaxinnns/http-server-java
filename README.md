# HTTP Server in Java

This is a simple HTTP server written in Java TCP primitives that supports the following endpoints:

- `/echo/{str}`: Returns the string `{str}` in the response body.
- `/files/{filename}`: Serves files from a specified directory and supports file creation via POST requests.

## Running the Server

Use the provided shell script `http_server.sh` to start the server. You can specify a directory (relative to your current working directory) for file operations with the `--directory` flag.

```bash
./http_server.sh --directory /hello/
```

This will start the server listening on port 4221 and use `{your-current-working-directory}/hello/` as the base directory for `/files/{filename}` operations.

## Testing Endpoints

Ensure the server has been started as per the instructions above.

1. Echo Endpoint

Test the /echo/{str} endpoint using curl:

```bash
curl -v http://localhost:4221/echo/abc
```

Expected Response:

```
HTTP/1.1 200 OK\r\n
Content-Type: text/plain\r\n
Content-Length: 3\r\n
\r\n
abc
```

2. Serve Existing Files

Create a file in the directory and test retrieving it:

```bash
mkdir -p ./hello
echo -n 'Hello, World!' > ./hello/foo
curl -i http://localhost:4221/files/foo
```

Expected Response:

```
HTTP/1.1 200 OK\r\n
Content-Type: application/octet-stream\r\n
Content-Length: 13\r\n
\r\n
Hello, World!
```

Test a non-existent file:

```bash
curl -i http://localhost:4221/files/non_existent_file
```

Expected Response:

```
HTTP/1.1 404 Not Found\r\n
\r\n
```

3. Create New Files

Send a POST request to create a new file:

```bash
curl -v --data "12345" -H "Content-Type: application/octet-stream" http://localhost:4221/files/file_123
```

Expected Response:

```
HTTP/1.1 201 Created\r\n
\r\n
```

Behaviour:

- The server will create a new file in the specified directory (e.g., ./hello), with the specified file name (e.g., file_123).
- If no directory was specified, the file is created in your current working directory.
- The file will contain the contents of the request body (e.g., 12345).
