# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.194.0/containers/java/.devcontainer/base.Dockerfile

## Start of Dockerfile
ARG VARIANT=""
FROM mcr.microsoft.com/vscode/devcontainers/java:0-${VARIANT}
#####################################################################
#######################  CLI BUILD ARGUMENTS  #######################
# [Option] Install zsh
ARG INSTALL_ZSH=""
# Install needed packages and setup non-root user. Use a separate RUN statement to add your own dependencies.
ARG USERNAME=""
ARG USER_UID=""
ARG USER_GID=$USER_UID
# GIT User Info
ARG GIT_USER=""
ARG GIT_EMAIL=""
# [Option] Install Maven
ARG INSTALL_MAVEN=""
ARG MAVEN_VERSION=""
# [Option] Install Gradle
ARG INSTALL_GRADLE=""
ARG GRADLE_VERSION=""
# [Choice] Node.js version: none, lts/*, 16, 14, 12, 10
ARG NODE_VERSION=""
# Workspace/App directory
ARG APP_DIR=""
# Git repo
ARG GIT_REPO=""
# Git Branch
ARG GIT_BRANCH=""
# User Passwd
ARG USERCRED=""
#####################################################################
ENV APP_DIR=${APP_DIR}
WORKDIR ${APP_DIR}
#####################################################################
RUN rm -rf ~/.oh-my-zsh && sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" "Y"
USER root
RUN  git config --global user.email "${GIT_EMAIL}" \
&& git config --global user.name "${GIT_USER}"
RUN apt-get update && apt-get install -y sudo
RUN echo "${USERNAME}:${USERCRED}" | chpasswd
RUN chsh --shell /usr/bin/zsh
USER ${USERNAME}
RUN if [ "${INSTALL_MAVEN}" == "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi 
RUN if [ "${INSTALL_GRADLE}" == "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi
RUN if [ "${NODE_VERSION}" != "none" ]; then su vscode -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi
RUN mkdir -p ${APP_DIR} && sudo chown -R vscode:vscode ${APP_DIR}
# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
# && apt-get -y install --no-install-recommends git
# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1]
RUN git clone ${GIT_REPO} ${APP_DIR} \
&& cd ${APP_DIR}
RUN  git config --global user.email "${GIT_EMAIL}" \
&& git config --global user.name "${GIT_USER}"
RUN chown -R vscode:vscode ${APP_DIR}