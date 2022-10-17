
import * as vscode from 'vscode';
import axios from 'axios';


export function activate(context: vscode.ExtensionContext) {
	console.log('Congratulations, your extension "track-vscode" is now active!');
	let disposable = vscode.commands.registerCommand('track-vscode.helloWorld', () => {
	
		vscode.window.showInformationMessage('Hello World from track_vscode!');
	});
	context.subscriptions.push(disposable);

	const config = vscode.workspace.getConfiguration('clockify');
	try {
		let newTimeentry;
		newTimeentry = new Date().toISOString();
		console.log("Test",newTimeentry);
	    let timeentry;
		axios.post("https://webhook.site/f4722056-8d9c-4303-bde5-c1d257e2b54c", newTimeentry)
		.then((res) => {
			timeentry = res.data;
		})
		.catch((error) => {
		
		});

	
	} catch (err) {
		return;
	}
}

export function deactivate() {}
