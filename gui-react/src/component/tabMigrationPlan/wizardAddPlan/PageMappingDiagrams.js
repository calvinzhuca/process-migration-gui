import React, { Component } from "react";

import { Button } from "patternfly-react";

import PageMappingDiagramsSvgPan from "./PageMappingDiagramsSvgPan";

export default class PageMappingDiagrams extends Component {
  displayDiagramButton(onclickAction, displayText) {
    return (
      <Button bsStyle="default" onClick={onclickAction}>
        {displayText}
      </Button>
    );
  }

  render() {
    const sourceShown = {
      display: this.props.sourceDiagramshown ? "block" : "none"
    };

    const targetShown = {
      display: this.props.targetDiagramshown ? "block" : "none"
    };

    const sourceDisplayText = this.props.sourceDiagramshown
      ? "Hide Source Diagram"
      : "Show Source Diagram";
    const targetDisplayText = this.props.targetDiagramshown
      ? "Hide Target Diagram"
      : "Show Target Diagram";

    return (
      <div className="form-group">
        {this.displayDiagramButton(
          this.props.sourceDiagramButtonClick,
          sourceDisplayText
        )}
        &nbsp;
        {this.displayDiagramButton(
          this.props.targetDiagramButtonClick,
          targetDisplayText
        )}
        <h2 style={sourceShown}>
          Source Process Definition Diagram
          <PageMappingDiagramsSvgPan
            svgcontents={this.props.sourceInfo.svgFile}
            previousSelector={this.props.sourcePreviousSelector}
            currentSelector={this.props.sourceCurrentSelector}
          />
        </h2>
        <h2 style={targetShown}>
          Target Process Definition Diagram
          <PageMappingDiagramsSvgPan
            svgcontents={this.props.targetInfo.svgFile}
            previousSelector={this.props.targetPreviousSelector}
            currentSelector={this.props.targetCurrentSelector}
          />
        </h2>
      </div>
    );
  }
}
