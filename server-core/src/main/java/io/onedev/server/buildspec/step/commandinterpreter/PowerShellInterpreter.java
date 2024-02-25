package io.onedev.server.buildspec.step.commandinterpreter;

import io.onedev.k8shelper.CommandFacade;
import io.onedev.k8shelper.PowerShellFacade;
import io.onedev.server.annotation.Code;
import io.onedev.server.annotation.Editable;
import io.onedev.server.annotation.Interpolative;
import io.onedev.server.model.support.administration.jobexecutor.JobExecutor;

import javax.validation.constraints.NotEmpty;

@Editable(order=300, name="PowerShell")
public class PowerShellInterpreter extends Interpreter {

	private static final long serialVersionUID = 1L;

	@Editable(order=110, description="Specify PowerShell commands to execute "
			+ "under the <a href='https://docs.onedev.io/concepts#job-workspace' target='_blank'>job workspace</a>.<br>"
			+ "<b class='text-warning'>NOTE: </b> OneDev checks exit code of the script to determine if step is successful. "
			+ "Since PowerShell always exit with 0 even if there are script errors, you should handle errors in the script "
			+ "and exit with non-zero code, or add line <code>$ErrorActionPreference = &quot;Stop&quot;</code> at start of "
			+ "your script<br>")
	@Interpolative
	@Code(language=Code.POWER_SHELL, variableProvider="suggestVariables")
	@NotEmpty
	@Override
	public String getCommands() {
		return super.getCommands();
	}

	@Override
	public void setCommands(String commands) {
		super.setCommands(commands);
	}
	
	@Override
	public CommandFacade getExecutable(JobExecutor jobExecutor, String jobToken, String image, 
									   String builtInRegistryAccessToken, boolean useTTY) {
		return new PowerShellFacade(image, builtInRegistryAccessToken, getCommands(), useTTY);
	}

}
