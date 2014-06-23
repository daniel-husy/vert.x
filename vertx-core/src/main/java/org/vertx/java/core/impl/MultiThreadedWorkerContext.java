/*
 * Copyright (c) 2011-2013 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.vertx.java.core.impl;

import java.util.concurrent.Executor;

public class MultiThreadedWorkerContext extends WorkerContext {

  private final Executor bgExec;

  public MultiThreadedWorkerContext(VertxInternal vertx, Executor orderedBgExec, Executor bgExec) {
    super(vertx, orderedBgExec);
    this.bgExec = bgExec;
  }

  public void execute(ContextTask task) {
    bgExec.execute(wrapTask(task, false));
  }

  @Override
  protected boolean isOnCorrectContextThread(boolean expectRightThread) {
    return false;
  }
}
