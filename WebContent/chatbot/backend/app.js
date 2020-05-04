require('dotenv').config();
const AssistantV1 = require('watson-developer-cloud/assistant/v1');
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();

app.use(cors())
app.use(bodyParser.json());
app.use(express.static('./public'));

const port = 3000;

const assistant = new AssistantV1({
    iam_apikey: 'N1Wy_LvlBViEMjNuWWfqD_ljerPcpCIJniXNfShsaMKp',
    version: '2019-08-16',
    url: 'https://gateway.watsonplatform.net/assistant/api'
});

app.post('/conversation/', (req, res) => {
    const { text, context = {} } = req.body;

    const params = {
        input: { text },
        workspace_id: 'd762afb2-67d3-43f2-87c4-8d1d6c37050b',
        context,
    };

    assistant.message(params, (err, response) => {
        if (err) {
            console.error(err);
            res.status(500).json(err);
        } else {
            res.json(response);
        }
    });
});

app.listen(port, () => console.log(`Running on port ${port}`));