package org.codehaus.mojo.natives.msvc;

/*
 * The MIT License
 *
 * Copyright (c) 2004, The Codehaus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

import org.codehaus.mojo.natives.NativeBuildException;
import org.codehaus.mojo.natives.c.CLinker;
import org.codehaus.mojo.natives.linker.LinkerConfiguration;
import org.codehaus.mojo.natives.util.EnvUtil;

import org.codehaus.plexus.util.cli.Commandline;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:dantran@gmail.com">Dan Tran</a>
 * @version $Id$
 */
public class MSVCLinker 
    extends CLinker
{

    private Map environmentVariables;
     
    protected void setEnvironmentVariables ( Map envs )
    {
        this.environmentVariables = envs;
    }
    
    protected Map getEnvironmentVariables()
    {
        if ( this.environmentVariables == null )
        {
            return new Properties ();
        }
        
        return this.environmentVariables;
    }    
    
    
	protected Commandline createLinkerCommandLine( List objectFiles, LinkerConfiguration config )
        throws NativeBuildException
	{
		if ( config.getExecutable() == null || config.getExecutable().trim().length() == 0 )
		{
			config.setExecutable ( "link" );
		}
				
		Commandline cl = super.createLinkerCommandLine( objectFiles, config );
		
        EnvUtil.setupCommandlineEnv( this.getEnvironmentVariables(), cl );
		
		return cl;
	}
	
	protected String getLinkerOutputOption()
	{
		return "/out:";
	}

}
