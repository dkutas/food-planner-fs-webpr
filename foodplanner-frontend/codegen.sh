#!/bin/bash

# Ensure that the script is being run from the correct directory (Optional but good practice)
SCRIPT_DIR=$(dirname "$0")
cd "$SCRIPT_DIR"

# Set the location of the OpenAPI 3 spec file and the output directory
OPENAPI_SPEC_PATH="../openapi/openapi.json"  # Path to your OpenAPI specification file
OUTPUT_DIR="./src/generated"     # Output directory for the Angular client

# Install OpenAPI Generator (if needed)
if ! command -v openapi-generator-cli &> /dev/null
then
    echo "OpenAPI Generator CLI not found. Installing it..."
    npm install @openapitools/openapi-generator-cli -g
fi

# Generate the Angular client using OpenAPI Generator
echo "Generating Angular client from OpenAPI specification..."
openapi-generator-cli generate \
    -i "$OPENAPI_SPEC_PATH" \
    -g typescript-angular \
    -o "$OUTPUT_DIR" \
    --additional-properties=ngVersion=19.2.0,typescriptVersion=5.7.2 \
    --skip-validate-spec

# Check if generation was successful
if [ $? -eq 0 ]; then
    echo "Angular client generated successfully in $OUTPUT_DIR"
else
    echo "Error generating the Angular client."
    exit 1
fi

# Optional: Open the generated client folder
# open "$OUTPUT_DIR"  # Uncomment this line if you want to open the generated folder after generation (for macOS)

exit 0
