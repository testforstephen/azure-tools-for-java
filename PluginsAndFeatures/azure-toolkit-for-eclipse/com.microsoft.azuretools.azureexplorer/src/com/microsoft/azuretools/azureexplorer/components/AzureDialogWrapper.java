/**
 * Copyright (c) Microsoft Corporation
 * 
 * All rights reserved. 
 * 
 * MIT License
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files 
 * (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.microsoft.azuretools.azureexplorer.components;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.microsoft.applicationinsights.web.internal.ApplicationInsightsHttpResponseWrapper;
import com.microsoft.azuretools.authmanage.models.SubscriptionDetail;
import com.microsoft.azuretools.telemetry.TelemetryProperties;
import com.microsoft.azuretools.telemetry.AppInsightsClient;

public abstract class AzureDialogWrapper extends Dialog implements AzureDialogGetProterties, TelemetryProperties {
	private SubscriptionDetail subscription;

	protected AzureDialogWrapper(Shell parentShell) {
		super(parentShell);
	}
	 
	protected AzureDialogWrapper(IShellProvider parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void okPressed() {
		sentTelemetry(OK);
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed() {
		sentTelemetry(CANCEL);
		super.cancelPressed();
	}
	
	public SubscriptionDetail getSubscription() {
		return subscription;
	}
	
	public void setSubscription(SubscriptionDetail subscription) {
		this.subscription = subscription;
	}
	
	@Override
	public Map<String, String> toProperties() {
		final Map<String, String> properties = new HashMap<>();

        if (this.getSubscription() != null) {
            if(this.getSubscription().getSubscriptionName() != null)  properties.put("SubscriptionName", this.getSubscription().getSubscriptionName());
            if(this.getSubscription().getSubscriptionId() != null)  properties.put("SubscriptionId", this.getSubscription().getSubscriptionId());
        }

        return properties;
	}
}